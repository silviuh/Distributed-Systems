# importing libraries
from PyQt5.QtWidgets import *
import sys


# creating a class
# that inherits the QDialog class
class Form(QDialog):

    # constructor
    def __init__(self):
        super(Form, self).__init__()

        self.form_data = {
            'title': '',
            'author': '',
            'publisher': '',
            'content': '',
        }

        # setting window title
        self.setWindowTitle("Python")

        # setting geometry to the window
        self.setGeometry(100, 100, 300, 400)

        # creating a group box
        self.formGroupBox = QGroupBox("Book details")

        # creating spin box to select age
        # creating combo box to select degre

        # creating a line edit
        self.titleLineEdit = QLineEdit()
        self.authorLineEdit = QLineEdit()
        self.publisherLineEdit = QLineEdit()
        self.textLineEdit = QLineEdit()

        # calling the method that create the form
        self.create_form()

        # creating a dialog button for ok and cancel
        self.buttonBox = QDialogButtonBox(QDialogButtonBox.Ok | QDialogButtonBox.Cancel)

        # adding action when form is accepted
        self.buttonBox.accepted.connect(self.get_info)

        # addding action when form is rejected
        self.buttonBox.rejected.connect(self.reject)

        # creating a vertical layout
        mainLayout = QVBoxLayout()

        # adding form group box to the layout
        mainLayout.addWidget(self.formGroupBox)

        # adding button box to the layout
        mainLayout.addWidget(self.buttonBox)

        # setting lay out
        self.setLayout(mainLayout)

    # get info method called when form is accepted
    def get_info(self):
        self.form_data['title'] = self.titleLineEdit.text()
        self.form_data['author'] = self.authorLineEdit.text()
        self.form_data['publisher'] = self.publisherLineEdit.text()
        self.form_data['content'] = self.textLineEdit.text()
        self.close()

    def get_data(self):
        return self.form_data

    # creat form method
    def create_form(self):
        # creating a form layout
        layout = QFormLayout()

        # adding rows
        # for name and adding input text
        layout.addRow(QLabel("Title"), self.titleLineEdit)
        layout.addRow(QLabel("Author"), self.authorLineEdit)
        layout.addRow(QLabel("Publisher"), self.publisherLineEdit)
        layout.addRow(QLabel("Text"), self.textLineEdit)

        # setting layout
        self.formGroupBox.setLayout(layout)


# main method
if __name__ == '__main__':
    # create pyqt5 app
    app = QApplication(sys.argv)

    # create the instance of our Window
    window = Form()

    # showing the window
    window.show()

    # start the app
    sys.exit(app.exec())
