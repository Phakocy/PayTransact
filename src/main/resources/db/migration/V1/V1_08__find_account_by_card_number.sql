IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'find_account_by_card_number')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE find_account_by_card_number
GO

CREATE PROCEDURE
    [dbo].[find_account_by_card_number] @card_number BIGINT,
                                        @id INT OUTPUT,
                                        @balance FLOAT OUTPUT,
                                        @cardNumber BIGINT OUTPUT,
                                        @accountNumber BIGINT OUTPUT,
                                        @user_Id INT OUTPUT,
                                        @date_created DATETIME OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT @id = id,
           @balance = balance,
           @cardNumber = card_number,
           @accountNumber = account_number,
           @user_Id = user_id,
           @date_created = date_created

    FROM [dbo].[accounts]
             WITH (NOLOCK)
    WHERE card_number = @card_number

END
GO