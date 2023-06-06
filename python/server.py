import sys
from flask import Flask, request, Response
import numpy as np
import pickle

app = Flask(__name__)


@app.route('/')
def home():
    return "API."

'''@app.route('/translate/', methods=['POST'])
def translate():
    
    try: 
        request_data = request.get_json()
    except:
        request_data = request.form
    
    en_text = list(request_data['en_text'])


    tokenized = translate_tokenizer.prepare_seq2seq_batch(en_text, return_tensors="pt")["input_ids"]
    german_translation = translate_model.generate(tokenized)

    german_text = translate_tokenizer.batch_decode(german_translation, skip_special_tokens=True)

    return german_text'''


@app.route('/compas/<x>')
def classify(x : np.ndarray):
    prediction  = model.predict(x)

    return prediction


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

    model = pickle.load(open(f'{model_option}.pk', 'rb'))

    app.run(debug=True, host=HOST, port=PORT)
