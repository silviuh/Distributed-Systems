import os
import sys
from PyQt5.QtWidgets import QWidget, QApplication, QFileDialog, QMessageBox
from PyQt5 import QtCore
from PyQt5.uic import loadUi

from Form import Form
from mq_communication import RabbitMq


def debug_trace(ui=None):
    from pdb import set_trace
    QtCore.pyqtRemoveInputHook()
    set_trace()
    # QtCore.pyqtRestoreInputHook()


class LibraryApp(QWidget):
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self):
        super(LibraryApp, self).__init__()
        ui_path = os.path.join(self.ROOT_DIR, 'exemplul_2.ui')
        loadUi(ui_path, self)

        self.form = Form()
        self.form.closeEvent = self.CloseEvent
        self.search_btn.clicked.connect(self.search)
        self.save_as_file_btn.clicked.connect(self.save_as_file)
        self.rabbit_mq = RabbitMq(self)
        self.add_book_btn.clicked.connect(lambda: self.form.exec())

    def CloseEvent(self, event):
        book_info = self.form.get_data()
        request = 'add:title={}:author={}:publisher={}:content={}'.format(
            book_info['title'],
            book_info['author'],
            book_info['publisher'],
            book_info['content']
        )
        print(request)
        self.send_request(request)

    def set_response(self, response):
        self.result.setText(response)

    def send_request(self, request):
        self.rabbit_mq.send_message(message=request)
        self.rabbit_mq.receive_message()

    def search(self):
        search_string = self.search_bar.text()
        request = None

        if self.json_rb.isChecked():
            request = 'print:json'

        elif self.html_rb.isChecked():
            request = 'print:html'

        elif self.xml_rb.isChecked():
            request = 'print:xml'

        else:
            request = 'print:raw'

        if self.author_rb.isChecked():
            request += (" " + 'find:author={}'.format(search_string))
        elif self.title_rb.isChecked():
            request += (" " + 'find:title={}'.format(search_string))
        else:
            request += (" " + 'find:publisher={}'.format(search_string))

        self.send_request(request)

    def save_as_file(self):
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        file_path = str(
            QFileDialog.getSaveFileName(self,
                                        'Salvare fisier',
                                        options=options))
        if file_path:
            file_path = file_path.split("'")[1]
            if not file_path.endswith('.json') and not file_path.endswith(
                    '.html') and not file_path.endswith('.txt') and not file_path.endswith('.xml'):
                if self.json_rb.isChecked():
                    file_path += '.json'
                elif self.html_rb.isChecked():
                    file_path += '.html'
                elif self.xml_rb.isChecked():
                    file_path += '.xml'
                else:
                    file_path += '.txt'
            try:
                with open(file_path, 'w') as fp:
                    if file_path.endswith(".html"):
                        fp.write(self.result.toHtml())
                    if file_path.endswith(".json"):
                        fp.write(self.result.toHtml())
                    if file_path.endswith(".xml"):
                        fp.write(self.result.toPlainText())
                    else:
                        fp.write(self.result.toPlainText())
            except Exception as e:
                print(e)
                QMessageBox.warning(self, 'Exemplul 2',
                                    'Nu s-a putut salva fisierul')


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = LibraryApp()
    window.show()
    sys.exit(app.exec_())
