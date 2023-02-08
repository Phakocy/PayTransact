IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'"find_account_by_account_id"')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE find_account_by_account_id
GO

CREATE PROCEDURE
    [dbo].[find_account_by_account_id] @accountId INT,
                                       @id INT OUTPUT,
                                       @date_created DATETIME OUTPUT,
                                       @balance FLOAT OUTPUT,
                                       @cardNumber BIGINT OUTPUT,
                                       @accountNumber BIGINT OUTPUT,
                                       @user_id INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT @id = id,
           @date_created = date_created,
           @balance = balance,
           @cardNumber = card_number,
           @accountNumber = account_number,
           @user_id = user_id

    FROM [dbo].[accounts]
             WITH (NOLOCK)
    WHERE id = @accountId

END
GO