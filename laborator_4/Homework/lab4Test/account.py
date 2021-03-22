class Account:

    def __init__(self, _id, _first_name, _last_name, _balance):
        self.id = _id
        self.first_name = _first_name
        self.last_name = _last_name
        self.balance = _balance

    def json(self):
        return {'id': self.id, 'first_name': self.first_name,
                'last_name': self.last_name, 'balance': self.balance}
