module Logger
    ( logit, logfile
    ) where

logfile :: String
logfile = "csv-parser.log"

logit ::  String -> IO String
logit msg  =  fmap (mapper msg) (writeFile logfile msg)

mapper :: String -> () -> String
mapper msg  () = msg
