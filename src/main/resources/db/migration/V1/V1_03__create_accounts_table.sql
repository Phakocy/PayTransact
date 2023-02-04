IF NOT EXISTS(
        SELECT *
        FROM sys.objects
        WHERE object_id = OBJECT_ID(N'[dbo].[accounts]')
          AND type IN (N'U')
    )
    BEGIN
        CREATE TABLE paytransact.dbo.accounts
        (
            id             int PRIMARY KEY IDENTITY (101, 1),
            date_created   datetime,

            balance        int NOT NULL,
            card_number    int NOT NULL,
            account_number int NOT NULL,
            user_id        int NOT NULL,
            FOREIGN KEY (user_id) REFERENCES paytransact.dbo.users ON DELETE CASCADE ON UPDATE CASCADE,
        )
    END
GO
