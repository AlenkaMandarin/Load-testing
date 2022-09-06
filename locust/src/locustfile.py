import time
from locust import HttpUser, task, between

class QuickstartUser(HttpUser):
    wait_time = between(1, 5)

    @task
    def get_token(self):
        self.client.verify = False
        self.client.post("/api/v1/test", {"username":"user", "password":"pass"})

