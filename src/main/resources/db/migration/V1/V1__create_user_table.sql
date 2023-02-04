IF NOT EXISTS(
        SELECT *
        FROM sys.objects
        WHERE object_id = OBJECT_ID(N'[dbo].[users]')
          AND type IN (N'U')
    )
    BEGIN
        CREATE TABLE paytransact.dbo.users
        (
            id           int PRIMARY KEY IDENTITY (101, 1),
            email        varchar(100) NOT NULL UNIQUE,
            password     varchar(30)  NOT NULL,
            date_created datetime,
        )
    END