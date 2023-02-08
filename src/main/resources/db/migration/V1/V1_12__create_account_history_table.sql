IF NOT EXISTS(
        SELECT *
        FROM sys.objects
        WHERE object_id = OBJECT_ID(N'[dbo].[account_history]')
          AND type IN (N'U')
    )
    BEGIN
        CREATE TABLE
            [paytransact].[dbo].[account_history]
        (
            id           INT PRIMARY KEY IDENTITY (101, 1),
            date_created DATETIME,

            body         VARCHAR(250) NOT NULL,
            account_id   INT          NOT NULL,

            CONSTRAINT FK_account_id_accounts__001 FOREIGN KEY (account_id) REFERENCES dbo.accounts (id)
                ON DELETE CASCADE
                ON UPDATE CASCADE,
        )
    END