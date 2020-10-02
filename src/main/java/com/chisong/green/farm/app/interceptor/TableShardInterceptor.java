package com.chisong.green.farm.app.interceptor;

import com.chisong.green.farm.app.utils.AppUtils;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-10-02 14:43
 */
@Intercepts({
	@Signature(
		type = Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
	)
})
@Component
@Slf4j
public class TableShardInterceptor implements Interceptor {


	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory REFLECTOR_FACTORY = new DefaultReflectorFactory();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("TableShardInterceptor -------------- ");
		MappedStatement mappedStatement =	(MappedStatement)invocation.getArgs()[0];
		BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
		String sql = boundSql.getSql();
		Long appId = AppUtils.get();
		log.info("appId == {}", appId);
		String upperSql = sql.toUpperCase();
		Pattern pattern = Pattern.compile(".*FROM\\s+APP_INFO.*");
		Pattern pattern2 = Pattern.compile(".*AND\\s+USER_NAME.*");
		Pattern pattern3 = Pattern.compile(".*FROM.*SUMMARY_INFO.*");
		if(pattern.matcher(upperSql).matches()
			|| pattern2.matcher(upperSql).find()
			|| pattern3.matcher(upperSql).find()){
			return invocation.proceed();
		}

		if(upperSql.contains("WHERE")){
			sql = sql.replaceAll("where|WHERE", "where app_info_id="+ appId +" and ");
		}else if(upperSql.toUpperCase().contains("ORDER") && upperSql.contains("BY")){
			sql = sql.replace("ORDER", "where app_info_id="+appId+" ORDER");
		}else{
			sql = sql +" where app_info_id="+appId;
		}
		log.info("sql == {}", sql);
		try {
			Field field = BoundSql.class.getDeclaredField("sql");
			field.setAccessible(true);
			field.set(boundSql, sql);
			field.setAccessible(false);
		}catch(Exception ex){
			log.error("反射字段处理失败", ex);
		}

		MappedStatement newMappedStatement = generalMappedStatement(mappedStatement, new BoundSqlSqlSource(boundSql));
		MetaObject msObject = MetaObject.forObject(newMappedStatement, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, REFLECTOR_FACTORY);
		msObject.setValue("sqlSource.boundSql.sql", sql);
		invocation.getArgs()[0] = newMappedStatement;


		return invocation.proceed();
	}

	public static void main(String[] args) {
		Pattern pattern2 = Pattern.compile(".*AND\\s+USER_NAME.*");
		String sql ="select\n"
			+ "         \n"
			+ "        'false' as QUERYID,\n"
			+ "         \n"
			+ "        id, nick_name, password, user_name, country, recommend_id, status, company_name,supplier_account,\n"
			+ "        supplier_id, type, province, city, avatar_url, mobile, union_id, open_id, create_time, update_time,\n"
			+ "         validity,app_info_id\n"
			+ "     \n"
			+ "        from customer_info\n"
			+ "         \n"
			+ "             \n"
			+ "         WHERE (  validity = ?\n"
			+ "                                \n"
			+ "                        \n"
			+ "                            \n"
			+ "                                    and user_name = ? )";
		log.info("{}",pattern2.matcher(sql.toUpperCase()).matches());
	}
	/**
	 * 创建新的 MappedStatement
	 *
	 * @param mappedStatement MappedStatement
	 * @param sqlSource       SqlSource
	 * @return MappedStatement
	 */
	private MappedStatement generalMappedStatement(MappedStatement mappedStatement, SqlSource sqlSource) {
		MappedStatement.Builder builder =
			new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), sqlSource, mappedStatement.getSqlCommandType());
		builder.resource(mappedStatement.getResource());
		builder.fetchSize(mappedStatement.getFetchSize());
		builder.statementType(mappedStatement.getStatementType());
		builder.keyGenerator(mappedStatement.getKeyGenerator());
		if (mappedStatement.getKeyProperties() != null && mappedStatement.getKeyProperties().length != 0) {
			StringBuilder keyProperties = new StringBuilder();
			for (String keyProperty : mappedStatement.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		builder.timeout(mappedStatement.getTimeout());
		builder.parameterMap(mappedStatement.getParameterMap());
		builder.resultMaps(mappedStatement.getResultMaps());
		builder.resultSetType(mappedStatement.getResultSetType());
		builder.cache(mappedStatement.getCache());
		builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
		builder.useCache(mappedStatement.isUseCache());

		return builder.build();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this); // 返回代理类
	}

	@Override
	public void setProperties(Properties properties) {

	}

	private class BoundSqlSqlSource implements SqlSource {
		private BoundSql boundSql;

		BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
}
