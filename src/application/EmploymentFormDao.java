package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class EmploymentFormDao {

	public int saveForm(EmploymentForm employ) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getDBConnection();
			connection.setAutoCommit(false);
			String query = "INSERT INTO applications(full_name, address, contact_number, email, education, gender, "
					+ "date_available, desired_position, desired_salary, authorized_to_work, has_relatives, "
					+ "relatives_explanation, signature) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int counter = 1;
			statement.setString(counter++, employ.getFullName());
			statement.setString(counter++, employ.getAddress());
			statement.setString(counter++, employ.getContactNumber());
			statement.setString(counter++, employ.getEmail());
			statement.setString(counter++, employ.getEducation());
			statement.setString(counter++, employ.getGender());
			if (employ.getDateAvailable() == null) {
				statement.setNull(counter++, Types.DATE);
			} else {
				statement.setDate(counter++, Date.valueOf(employ.getDateAvailable()));
			}
			statement.setString(counter++, employ.getDesiredPosition());
			statement.setString(counter++, employ.getDesiredSalary());
			statement.setBoolean(counter++, employ.isAuthorizedToWork());
			statement.setBoolean(counter++, employ.isHasRelatives());
			statement.setString(counter++, employ.getRelativesExplanation());
			statement.setString(counter++, employ.getSignature());
			statement.executeUpdate();
			connection.commit();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException exception) {
			if (null != connection) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (null != resultSet) {
				resultSet.close();
			}
			if (null != statement) {
				statement.close();
			}
			if (null != connection) {
				connection.close();
			}
		}
		return 0;
	}
}