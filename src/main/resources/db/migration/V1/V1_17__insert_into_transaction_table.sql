IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'insert_into_transaction_table')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE insert_into_transaction_table
GO

CREATE PROCEDURE
    [dbo].[insert_into_transaction_table] @transaction_id INT OUTPUT,
                                          @amount FLOAT,
                                          @balance FLOAT,
                                          @narration VARCHAR(250),
                                          @status VARCHAR(50),
                                          @account_id INT,
                                          @date_created DATETIME
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO [dbo].[transactions]
    (amount,
     balance,
     narration,
     status,
     account_id,
     date_created)

    VALUES (@amount,
            @balance,
            @narration,
            @status,
            @account_id,
            @date_created)

    SELECT @transaction_id = @@IDENTITY

END
GO