IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'update_into_account_table')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE update_into_account_table
GO

CREATE PROCEDURE
    [dbo].[update_into_account_table] @accountId INT,
                                      @balance FLOAT,
                                      @account_id INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE [dbo].[accounts]
    SET balance = @balance
    WHERE id = @accountId

    SELECT @account_id = @@IDENTITY

END
GO