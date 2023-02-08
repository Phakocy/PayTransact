IF NOT EXISTS(
        SELECT *
        FROM sys.objects
        WHERE object_id = OBJECT_ID(N'[dbo].[transactions]')
          AND type IN (N'U')
    )
    BEGIN
        CREATE TABLE
            [paytransact].[dbo].[transactions]
        (
            id           INT PRIMARY KEY IDENTITY (101, 1),
            date_created DATETIME,

            amount       FLOAT        NOT NULL,
            balance      FLOAT        NOT NULL,
            narration    VARCHAR(MAX) NOT NULL,
            status       VARCHAR(50)  NOT NULL,

            account_id   INT          NOT NULL,

            CONSTRAINT FK_account_id_accounts__002 FOREIGN KEY (account_id) REFERENCES dbo.accounts (id)
                ON DELETE CASCADE
                ON UPDATE CASCADE,
        )
    END