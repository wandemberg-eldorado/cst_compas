import sys
from flask import Flask
import numpy as np
import pickle
import json

app = Flask(__name__)


@app.route('/')
def home():
    return "API."


@app.route('/classify/<x>')
def classify(x):
    print(x)
    x= np.array(json.loads(x)).reshape(1,-1)
    prediction  = model.predict(x)
    #print(prediction)
    return json.dumps(prediction.tolist())
    #return '0'


def split(string): 
    li = list(string.split(":")) 
    return li



if __name__ == "__main__":
    msg = '''
        usage: python3 server.py <ip>:<port> <model>
        Model options are:
        - nearest_neighbors
        - linear_svm
        - decision_tree
        - random_forest
        - mlp
        - adaboost
    '''
    print(msg)
    
    args = sys.argv[1:]
    HOST = split(args[0])[0]
    PORT = int(split(args[0])[1])
    model_option = args[1]

    model = pickle.load(open(f'models/{model_option}.pk', 'rb'))

    app.run(debug=True, host=HOST, port=PORT)
