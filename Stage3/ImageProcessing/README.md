# ImageProcessing
<!-- TOC -->

- [ImageProcessing](#imageprocessing)
    - [Preparation](#preparation)
    - [Usage](#usage)
    - [ImageProcessing](#imageprocessing-1)
        - [goal](#goal)
        - [target](#target)
        - [scr](#scr)
            - [MyImageIO](#myimageio)
            - [MyImageProcessor](#myimageprocessor)
            - [ImageReaderRunner](#imagereaderrunner)
            - [ImageProcessTest](#imageprocesstest)

<!-- /TOC -->

## Preparation

1. ```Number.jar``` is required.
1. ```ImageReader.jar``` is required.

## Usage

1. ```ant compile```   
    Compile all the java files into the folders automaticaly layered
1. ```cd class```  
    Move to the class folder.  
1. ```java ImageReaderRunner```  
    Run the runner.

## ImageProcessing

Read a bitmap from binary stream, process the image with preferred RGB chanel and save the processed image as bitmap file.

### goal

Store the given RBG chanel processed bitmap image for given bitmap image.

### target

Store the given bitmap image to test.

### scr

The java code files.

#### MyImageIO

Read bitmap image from binary stream.  
Save processed image as bitmap image.

#### MyImageProcessor

Process the chosen bitmap image by given RGB chanel.

#### ImageReaderRunner

A GUI for process the bitmap image.

#### ImageProcessTest

The JUint test code file to test the image processing file with the given processed file whethert their width, height and pixel are equal.