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

![image](https://user-images.githubusercontent.com/80216049/221704548-4bb8fc7f-c068-49dc-863b-f85e108e6cd5.png)

### ChoiceEntity

The ChoiceEntity represents a single possible choice of response returned by the ChatGPT API. It has fields such as text, index, logprobs, and finish_reason which provide information about the content and likelihood of the response.

![image](https://user-images.githubusercontent.com/80216049/221704913-f2026d3f-735b-432c-9aa2-41d7521746c7.png)

### UsageEntity

The UsageEntity represents the usage statistics of the ChatGPT API during the response generation process. It has fields such as prompt_token, completion_tokens, and total_tokens which provide information about the number of tokens used in generating the response.

![image](https://user-images.githubusercontent.com/80216049/221705427-48a41853-a51d-4797-bf74-dbc789041286.png)

## Services

The ChatgptService interface defines the contract for the service that communicates with the ChatGPT API and writes to a CSV file. It has three methods:
sendChatgptRequest
writeToCSV
sendPrompt

![image](https://user-images.githubusercontent.com/80216049/221705814-309e684c-d761-44d0-a142-44c067e6d5eb.png)

### sendChatgptRequest

This method in the ChatgptService interface implementation sends a POST request to the ChatGPT API using the provided body as the request body. It sets the necessary headers such as the Content-Type and Authorization headers with the openaiApiKey provided in the application properties file. It then uses the HttpClient to send the request and wait for the response. The response body is returned as a string.

![image](https://user-images.githubusercontent.com/80216049/221706657-01d9d778-a059-41e6-a1ac-b55bf705ada8.png)

### writeToCSV

This class is responsible for writing the question and answer pairs to a CSV file. It takes the file path and a QADto object containing the question and answer as parameters. It checks if the file exists, and if it doesn't, it writes a header to the file. It then appends the new question and answer to the file in CSV format. If an exception occurs during the writing process, it will print the stack trace.

![image](https://user-images.githubusercontent.com/80216049/221706913-b1b6010a-a81e-4135-b5e7-5f411c29f090.png)

### sendPrompt

This method sends a prompt to the GPT API using the sendChatgptRequest method and retrieves the response in the form of an AnswerEntity. It then extracts the answer from the response by accessing the ChoiceEntity list within the AnswerEntity and getting the text of the first element. The method then creates a QADto object containing the prompt and extracted answer and writes it to a CSV file using the writeToCSV method. Finally, it returns the QADto object.

![image](https://user-images.githubusercontent.com/80216049/221707204-40d93bbd-36d1-4970-8b88-d8cf7f814abf.png)

## Controllers

This class is a Spring REST controller that handles HTTP requests for the ChatGPT microservice. It maps HTTP requests to the "/response" endpoint to the response() method, which takes a JSON request body containing a PromptDto object, sends the prompt to the ChatGPT API using the ChatgptService class, and returns a JSON response body containing a QADto object that includes the question and answer generated by the ChatGPT API. The @PostMapping annotation specifies that this method should handle HTTP POST requests to the "/response" endpoint, while the @Autowired annotation injects an instance of the ChatgptService class into the controller. The @RestController and @RequestMapping annotations are used to define this class as a REST controller and set the base path for the endpoint mappings.

![image](https://user-images.githubusercontent.com/80216049/221707482-b6bcd7ca-322e-456c-94a5-66c1b33bfc43.png)

## File.csv

![image](https://user-images.githubusercontent.com/80216049/221707704-14647c03-2d29-4aa6-891d-d3c6b793a829.png)

## Dockerization of microservice

Dockerization is the process of packaging a microservice application and its dependencies into a Docker image. The Docker image can then be deployed to any environment that has Docker installed, making the application easily portable and consistent across different environments.

### Dockerfile

![image](https://user-images.githubusercontent.com/80216049/221709761-131b5cf5-b403-41b5-a8c4-a2decad6ee50.png)

we are using the OpenJDK 17 Alpine Linux base image, which is a lightweight image that includes the Java Runtime Environment. We are exposing port 8081, which is the port on which the microservice application will listen for incoming requests. We are also using an ARG instruction to specify the name and location of the executable JAR file for the microservice application, which is then copied to the image using the ADD instruction.

Finally, we define the ENTRYPOINT instruction, which specifies the command that will be run when the container starts. In this case, the command will run the Java executable JAR file using the java command with the -jar option and passing the path to the app.jar file as an argument. This will start the microservice application and make it available on port 8081.

### CREATE .JAR

![WhatsApp Image 2023-02-28 at 00 02 26](https://user-images.githubusercontent.com/80216049/221710825-7eb354ca-4d02-4e67-a209-e15d12432f45.jpeg)

### build dockerFile

![WhatsApp Image 2023-02-28 at 00 05 30](https://user-images.githubusercontent.com/80216049/221711045-d045532f-2cc4-4213-9d07-18274b090da7.jpeg)


![WhatsApp Image 2023-02-27 at 23 54 52](https://user-images.githubusercontent.com/80216049/221711069-d02eaee7-4a0a-45f2-bbb2-84a3b1a73719.jpeg)
