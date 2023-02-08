IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'get_account_history')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE get_account_history
GO

CREATE PROCEDURE
    [dbo].[get_account_history] @accountId INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT *
    FROM [dbo].[account_history]
    WHERE account_id = @accountId

END
GO