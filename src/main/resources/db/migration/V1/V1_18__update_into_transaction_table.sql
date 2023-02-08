IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'update_into_transaction_table')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE update_into_transaction_table
GO

CREATE PROCEDURE
    [dbo].[update_into_transaction_table] @transaction_id INT,
                                          @balance FLOAT,
                                          @status VARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE [dbo].[transactions]

    SET balance = @balance,
        status  = @status

    WHERE id = @transaction_id

    SELECT @transaction_id = @@IDENTITY

END
GO