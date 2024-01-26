package com.tokio.technicaltest.integration;

public interface IntegrationHelper {

    String QUERY_INSERT_TRANSFER_RATES = "insert into transfer_rate (id, from_day, until_day, transfer_rate, status) " +
            "values (1, 0, 0, 2.5, true), (2, 1, 10, 0, true), (3, 11, 20, 8.2, true), (4, 21, 30, 6.9, true), " +
            "(5, 31, 40, 4.7, true), (6, 41, 50, 1.7, true)";
    String QUERY_INSERT_SCHEDULES = "insert into scheduling (uuid, origin_account, destination_account, " +
            "transfer_amount, scheduling_date, transfer_date, transfer_status, transfer_rate_id) " +
            "values ('0f373186-17fe-4984-8780-f5c423955461', 1000, 10001, '100.00', '2024-01-25', '2024-01-25', 0," +
            "1), ('0f373186-17fe-4984-8780-f5c423955462', 1000, 1002, '200.00', '2024-01-26', '2024-01-25', 0, 2);";


    String QUERY_DELETE_SCHEDULES = "delete from scheduling;";



}
