IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'find_user_by_email')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE find_user_by_email
GO

CREATE PROCEDURE [dbo].[find_user_by_email] @email VARCHAR(200)
AS
    SET NOCOUNT ON;


select *
from [dbo].[users] (NOLOCK)
where email = @email