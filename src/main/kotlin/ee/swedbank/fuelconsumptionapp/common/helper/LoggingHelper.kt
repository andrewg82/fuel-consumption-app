package ee.swedbank.fuelconsumptionapp.common.helper

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Adds a log extension field to all classes
 */
val Any.log: Logger
    get() = LoggerFactory.getLogger(this.javaClass)