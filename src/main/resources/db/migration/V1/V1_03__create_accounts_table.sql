IF NOT EXISTS(
        SELECT *
        FROM sys.objects
        WHERE object_id = OBJECT_ID(N'[dbo].[accounts]')
          AND type IN (N'U')
    )
    BEGIN
        CREATE TABLE [paytransact].[dbo].[accounts]
        (
            id             INT PRIMARY KEY IDENTITY (101, 1),
            date_created   DATETIME,

            balance        FLOAT  NOT NULL,
            card_number    BIGINT NOT NULL UNIQUE,
            account_number BIGINT NOT NULL UNIQUE,
            user_id        INT    NOT NULL UNIQUE,

            CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES dbo.users (id)
                ON DELETE CASCADE
                ON UPDATE CASCADE,
        )
    END
GO
