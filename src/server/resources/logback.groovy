import ch.qos.logback.classic.filter.ThresholdFilter

appender('FILE', FileAppender) {
   file = 'error.log'
   append = true
   filter(ThresholdFilter) {
      level = 'ERROR'
   }
   encoder(PatternLayoutEncoder) {
      pattern = '%-5relative %-5level %logger{35} - %msg%n'
   }
}

appender('STDOUT', ConsoleAppender) {
   filter(ThresholdFilter) {
      level = 'ERROR'
   }
   encoder(PatternLayoutEncoder) {
      pattern = '%-5relative %-5level %logger{35} - %msg%n'
   }
}
 
root(DEBUG, ['FILE', 'STDOUT' ])