# chatgpt_microservice
## Introduction

The purpose of this project is to create a Java microservice that communicates with ChatGPT, an AI platform from OpenAI, to answer user questions and append the results to a CSV file. The microservice is designed to receive user input through an API endpoint, create a request to ChatGPT, extract the answer from the response, and then store the question and answer in a CSV file. The CSV file is stored in a volume that maps a location in the host to a location in the container, ensuring that it can be persisted and appended to even after the container is stopped

This report outlines the design and implementation of the microservice, including the technologies used, the architecture, and the individual components such as the controller, service, repository, and entity classes. We also discuss how the microservice communicates with the ChatGPT API, including the request format and authentication requirements. Finally, we present the results of testing the microservice, including its performance and functionality.

Overall, the microservice provides a useful and scalable solution for answering user questions and storing the results in a persistent format, leveraging the power of AI and cloud technologies to enhance user experience and data management.

![image](https://user-images.githubusercontent.com/80216049/221702998-537cdd01-34a5-4153-8345-dd5110539baf.png)
