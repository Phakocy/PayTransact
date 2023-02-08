IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'find_user_by_email')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE find_user_by_email
GO

CREATE PROCEDURE
    [dbo].[find_user_by_email] @_email VARCHAR(200),
                               @id INT OUTPUT,
                               @email VARCHAR(200) OUTPUT,
                               @date_created DATETIME OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT @id = id,
           @email = email,
           @date_created = date_created
    FROM [dbo].[users]
             WITH (NOLOCK)
    WHERE email = @_email

END
GO