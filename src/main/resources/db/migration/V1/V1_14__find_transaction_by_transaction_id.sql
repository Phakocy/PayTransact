IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'find_transaction_by_transaction_id')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE find_transaction_by_transaction_id
GO

CREATE PROCEDURE
    [dbo].[find_transaction_by_transaction_id] @transactionId INT,
                                               @id INT OUTPUT,
                                               @account_id INT OUTPUT,
                                               @balance FLOAT OUTPUT,
                                               @amount FLOAT OUTPUT,
                                               @narration VARCHAR(MAX) OUTPUT,
                                               @status VARCHAR(MAX) OUTPUT,
                                               @date_created DATETIME OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT @id = id,
           @account_id = account_id,
           @balance = balance,
           @amount = amount,
           @narration = narration,
           @status = status,
           @date_created = date_created

    FROM [dbo].[transactions]
             WITH (NOLOCK)
    WHERE id = @transactionId

END
GO