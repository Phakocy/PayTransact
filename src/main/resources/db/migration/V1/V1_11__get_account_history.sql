IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'get_account_history')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE get_account_history
GO

CREATE PROCEDURE [dbo].[get_account_history] @id INT OUTPUT,
                                             @body FLOAT OUTPUT,
                                             @account_id INT OUTPUT,
                                             @date_created DATETIME OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT id           = @id,
           body         = @body,
           account_id   = @account_id,
           date_created = @date_created
    FROM [dbo].[account_history]

END
GO