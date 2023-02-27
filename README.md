# chatgpt_microservice
## Introduction

The purpose of this project is to create a Java microservice that communicates with ChatGPT, an AI platform from OpenAI, to answer user questions and append the results to a CSV file. The microservice is designed to receive user input through an API endpoint, create a request to ChatGPT, extract the answer from the response, and then store the question and answer in a CSV file. The CSV file is stored in a volume that maps a location in the host to a location in the container, ensuring that it can be persisted and appended to even after the container is stopped

This report outlines the design and implementation of the microservice, including the technologies used, the architecture, and the individual components such as the controller, service, repository, and entity classes. We also discuss how the microservice communicates with the ChatGPT API, including the request format and authentication requirements. Finally, we present the results of testing the microservice, including its performance and functionality.

Overall, the microservice provides a useful and scalable solution for answering user questions and storing the results in a persistent format, leveraging the power of AI and cloud technologies to enhance user experience and data management.

![image](https://user-images.githubusercontent.com/80216049/221702998-537cdd01-34a5-4153-8345-dd5110539baf.png)

## Entities 

The entities in a software system represent the key components and data structures that enable the system to function properly. In this project, there are four main entities that play important roles in the microservice that interacts with the ChatGPT API.

### AnswerEntity

The AnswerEntity represents the answer returned by the ChatGPT API. It has fields such as id, object, created, and model which provide metadata about the response. It also has a list of ChoiceEntity objects which represent the different possible answers returned by the API. Additionally, it contains a UsageEntity object which gives information about the usage of the API during the response generation process.

![image](https://user-images.githubusercontent.com/80216049/221704070-35a26ac6-7c0d-4fc0-99fb-243c8fd1bd1a.png)

### CallEntity

The CallEntity represents a call to the ChatGPT API. It has fields such as model, prompt, max_tokens, and temperature which provide the parameters to be used for generating a response from the API.
