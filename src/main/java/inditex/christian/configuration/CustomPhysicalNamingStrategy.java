package inditex.christian.configuration;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnv) {
		return this.convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnv) {
		return this.convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnv) {
		return this.convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnv) {
		return this.convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnv) {
		return this.convertToSnakeCase(identifier);
	}

	private Identifier convertToSnakeCase(Identifier identifierFromHibernate) {

		Identifier identifier = null;

		if (identifierFromHibernate != null) {
			String newName = identifierFromHibernate.getText()
			        .replaceAll("([a-z])([A-Z])", "$1_$2")
			        .toUpperCase(Locale.ROOT);

			identifier = Identifier.toIdentifier(newName);
		}
		return identifier;
	}
}
