IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'find_transactions_by_account_id')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE find_transactions_by_account_id
GO

CREATE PROCEDURE
    [dbo].[find_transactions_by_account_id] @account_id INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT *

    FROM [dbo].[transactions]
             WITH (NOLOCK)

    WHERE account_id = @account_id

END
GO