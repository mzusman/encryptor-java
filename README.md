# _Encryptor_
[![Build Status](https://travis-ci.org/mzusman/encryptor-java.svg?branch=master)](https://travis-ci.org/mzusman/encryptor-java)
#### Prerequisities: 
- Java + JDK 8
- Maven 3

#### Usage:

```bash
git clone https://github.com/mzusman/encryptor-java.git
mvn install
java -jar target/encryptor-1.0.jar <enc|dec> <s|a> <file>
	Usage:
    	- <enc | dec> - encrypt or decrypt
    	- <s | a> - run in synchronized mode or asynchronized mode
    	- <file> - the file\directory that the software will operate on
```
