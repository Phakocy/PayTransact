IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'insert_into_history_table')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE insert_into_history_table
GO

CREATE PROCEDURE
    [dbo].[insert_into_history_table] @account_id INT,
                                      @body VARCHAR(250),
                                      @date_created DATETIME
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO [dbo].[account_history]
    (account_id,
     body,
     date_created)
    VALUES (@account_id,
            @body,
            @date_created)

END
GO