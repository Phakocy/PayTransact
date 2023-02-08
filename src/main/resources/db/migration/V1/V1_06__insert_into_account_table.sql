IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'insert_into_account_table')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE insert_into_account_table
GO

CREATE PROCEDURE
    [dbo].[insert_into_account_table] @dateCreated DATETIME,
                                      @balance FLOAT,
                                      @cardNumber BIGINT,
                                      @accountNumber BIGINT,
                                      @userId INT,
                                      @account_id INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO [dbo].[accounts]
    (date_created,
     balance,
     card_number,
     account_number,
     user_id)

    VALUES (@dateCreated,
            @balance,
            @cardNumber,
            @accountNumber,
            @userId)

    SELECT @account_id = @@IDENTITY

END
GO