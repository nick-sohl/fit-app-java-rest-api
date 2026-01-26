package com.fitapp.httpServer.infrastructure.adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.fitapp.httpServer.application.port.MeasurementRepositoryInterface;
import com.fitapp.httpServer.domain.entity.Measurement;
import com.fitapp.httpServer.infrastructure.shared.EstablishConnection;

public class MeasurementRepositoryAdapter implements MeasurementRepositoryInterface {

  private static final String FIND_BY_ID_SQL = """
        SELECT
            measurement_id,
            client_id,
            measured_at
        FROM measurements
        WHERE measurement_id = ?
      """;

  private static final String CREATE_RECORD_SQL = """
        INSERT INTO measurements (client_id, measured_at)
        VALUES (?, ?);
      """;

  @Override
  public List<Measurement> findAllMeasurements() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAllMeasurements'");
  }

  @Override
  public Optional<Measurement> findMeasurementById(Long measurementId) {

    try (Connection connection = EstablishConnection.establishConnection();
        PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {

      ps.setLong(1, measurementId);

      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next()) {
          return Optional.empty();
        }

        Measurement measurement = mapRow(rs);
        return Optional.of(measurement);
      }

    } catch (SQLException e) {
      throw new RuntimeException("Failed to query measurement", e);
    }
  }

  private Measurement mapRow(ResultSet rs) throws SQLException {
    Measurement m = new Measurement();
    m.setMeasurementId(rs.getLong("measurement_id"));
    m.setClientId(rs.getLong("client_id"));
    m.setMeasuredAt(rs.getObject("measured_at", Instant.class));
    return m;
  }

  @Override
  public Measurement addMeasurement(Measurement measurement) {

    try (Connection connection = EstablishConnection.establishConnection();
        PreparedStatement ps = connection.prepareStatement(CREATE_RECORD_SQL, Statement.RETURN_GENERATED_KEYS)) {

      // Set Values in SQL statement
      ps.setLong(1, measurement.getClientId());
      ps.setObject(2, measurement.getMeasuredAt());

      // Execute the SQL statement
      ps.executeUpdate();

      // Get the generated id from the DB
      ResultSet keys = ps.getGeneratedKeys();

      // Add ID to Entity
      if (keys.next()) {
        Long id = keys.getLong("mid");
        measurement.setMeasurementId(id);
      }

      return measurement;

    } catch (SQLException e) {
      // TODO: Implement Result Pattern
      throw new RuntimeException("Execution of SQL statement failed.\n", e);
    }

  }

  @Override
  public Measurement updateMeasurement(Measurement measurement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateMeasurement'");
  }

  @Override
  public void deleteMeasurement(int mid) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteMeasurement'");
  }

}
