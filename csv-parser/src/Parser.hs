module Parser
    ( parse
    ) where

import           Data.List.Split

parse :: String -> [String]
parse = splitOn ","
