package com.sd.laborator.interfaces

import org.springframework.context.annotation.Primary

interface LibraryPrinter: HTMLPrinter, JSONPrinter, RawPrinter, XMLPrinter