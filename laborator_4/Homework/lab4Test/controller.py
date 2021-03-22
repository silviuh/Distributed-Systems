from flask import Flask, render_template, request, redirect
from sqlalchemy.testing import db

from accounts_service import Account, AccountService

app = Flask(__name__)
account_service = AccountService()


@app.route('/')
@app.route('/index')
def index():
    global account_service
    # accounts = [
    #     {
    #         'id': 1,
    #         'first_name': 'test title 1',
    #         'last_name': 'test desc 1',
    #         'balance': 2323
    #     },
    #     {
    #         'id': 2,
    #         'first_name': 'test title 2',
    #         'last_name': 'test desc 2',
    #         'balance': 343.3
    #     }
    # ]
    accounts = account_service.get_accounts()
    # entries = Entry.query.all()
    return render_template('index.html', accounts=accounts)


@app.route('/add', methods=['POST'])
def add():
    global account_service

    if request.method == 'POST':
        form = request.form
        id_ = int(form.get('id'))
        first_name = form.get('first_name')
        last_name = form.get('last_name')
        balance = form.get('balance')

        if not account_service.id_exists(id_):
            account_service.add_account(id_, first_name, last_name, balance)
            return redirect('/')

    return render_template("404.html")


@app.route('/update', methods=['POST', 'PUT'])
def update():
    global account_service

    if request.method in ['PUT', 'POST']:
        form = request.form
        id_ = int(form.get('id'))
        first_name = form.get('first_name')
        last_name = form.get('last_name')
        balance = form.get('balance')

        if account_service.id_exists(id_):
            account_service.update_account(id_, first_name, last_name, balance)
            return redirect('/')

    return render_template("404_account_not_found.html")


@app.route('/delete', methods=['POST', 'DELETE'])
def delete():
    global account_service

    if request.method in ['DELETE', 'POST']:
        form = request.form
        id_ = int(form.get('id'))

        if account_service.id_exists(id_):
            account_service.delete_account(id_)
            return redirect('/')

    return render_template("404_account_not_found.html")


@app.route('/fetch', methods=['GET', 'POST'])
def fetch():
    global account_service

    if request.method in ['GET', 'POST']:
        id_ = int(request.args.get('id'))
        if account_service.id_exists(id_):
            account = account_service.get_account(id_)
            return render_template('account.html', account=account)

    return render_template("404_account_not_found.html")


if __name__ == '__main__':
    app.run(debug=True)
