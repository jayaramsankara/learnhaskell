module Main where

import           Control.Monad
import           Data.List
import           Data.List.Split
import           Logger
import           Parser
import           System.IO

main :: IO String
main = do
  putStrLn "Enter the CSV file name (Fully Qualified Name) : "
  filePath <- getLine
  csvLines <- readFile filePath
  let linesList =  splitOn "\n" csvLines

  -- TODO Implement filterM yourself
  filteredList <- filterM  logAndPrefixCheck linesList

  let parsedContent = filteredList >>= mapToToken
  print ("Check "++Logger.logfile)
  print (parsedContent)
  return (show parsedContent)


isCVDPrefix :: String -> Bool
isCVDPrefix = isPrefixOf "CVD"

logAndPrefixCheck :: String -> IO Bool
logAndPrefixCheck msg = do
                            loggedMsg <- logit msg
                            return (isCVDPrefix loggedMsg)

mapToToken :: String -> [String]
mapToToken = Parser.parse
