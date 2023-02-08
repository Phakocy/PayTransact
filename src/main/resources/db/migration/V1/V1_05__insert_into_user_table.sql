IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'insert_into_user_table')
            AND type IN (N'P', N'PC'))
    DROP PROCEDURE insert_into_user_table
GO

CREATE PROCEDURE
    [dbo].[insert_into_user_table] @email VARCHAR(200),
                                   @password VARCHAR(200),
                                   @date_created DATETIME
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO [dbo].[users]
    VALUES (@email,
            @password,
            @date_created)

END
GO