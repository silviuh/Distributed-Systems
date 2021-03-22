from account import Account


class AccountService:

    def __init__(self):
        self.accounts = [
            Account(1, 'John', 'Jackson', 3334.5),
            Account(2, 'Mike', 'Larson', 3222224.5)
        ]

    def get_accounts(self):
        return self.accounts

    def id_exists(self, _id):
        for account in self.accounts:
            if account.id == _id:
                return True
        return False

    def add_account(self, _id, _first_name, _last_name, _balance):
        new_account = Account(_id, _first_name, _last_name, _balance)
        self.accounts.append(new_account)

    def update_account(self, _id, _first_name, _last_name, _balance):
        account_index = 0
        for index, account in enumerate(self.accounts):
            if account.id == _id:
                account_index = index

        self.accounts[account_index].first_name = _first_name
        self.accounts[account_index].last_name = _last_name
        self.accounts[account_index].balance = _balance

    def delete_account(self, _id):
        account_index = 0
        for index, account in enumerate(self.accounts):
            if account.id == _id:
                account_index = index

        self.accounts.pop(account_index)

    def get_account(self, _id):
        account_index = 0
        for index, account in enumerate(self.accounts):
            if account.id == _id:
                account_index = index

        return self.accounts[account_index]
