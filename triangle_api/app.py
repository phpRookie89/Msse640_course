from flask import Flask, request, jsonify  # type: ignore
from triangle_model import Triangle
from triangle_data import save_triangle, get_all_triangles

app = Flask(__name__)


@app.route('/triangles', methods=['GET'])
def list_triangles():

    return jsonify(get_all_triangles())


@app.route('/triangles', methods=['POST'])
def create_triangle():

    data = request.get_json()

    if not data:
        return jsonify({"error": "Missing JSON body"}), 400

    a = data.get("a")
    b = data.get("b")
    c = data.get("c")

    if a is None or b is None or c is None:
        return jsonify({"error": "Missing triangle sides"}), 400

    triangle = Triangle(a, b, c)

    triangle_type = triangle.classify()

    record = {
        "a": a,
        "b": b,
        "c": c,
        "triangleType": triangle_type
    }

    save_triangle(record)

    return jsonify(record), 201


if __name__ == "__main__":
    app.run(debug=True)
