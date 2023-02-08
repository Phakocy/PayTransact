IF NOT EXISTS(
        SELECT *
        FROM sys.objects
        WHERE object_id = OBJECT_ID(N'[dbo].[users]')
          AND type IN (N'U')
    )
    BEGIN
        CREATE TABLE
            [paytransact].[dbo].[users]
        (
            id           INT PRIMARY KEY IDENTITY (101, 1),
            email        VARCHAR(100) NOT NULL UNIQUE,
            password     VARCHAR(50)  NOT NULL,
            date_created DATETIME,
        )
    END