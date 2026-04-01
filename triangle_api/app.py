from flask import Flask, jsonify, request
from triangle_model import Triangle
from triangle_data import save_triangle, get_all_triangles

app = Flask(__name__)

triangles = [
    {"id": 1, "a": 3, "b": 4, "c": 5, "type": "scalene"},
    {"id": 2, "a": 5, "b": 5, "c": 5, "type": "equilateral"},
    {"id": 3, "a": 5, "b": 5, "c": 8, "type": "isosceles"}
]


@app.route('/health', methods=['GET'])
def health_check():
    return jsonify({"status": "ok", "message": "Triangle API running"}), 200


@app.route('/triangles', methods=['GET'])
def get_triangles():
    return jsonify(triangles), 200


@app.route('/triangles/<int:id>', methods=['GET'])
def get_triangle_by_id(id):
    for triangle in triangles:
        if triangle["id"] == id:
            return jsonify(triangle), 200
    return jsonify({"error": "Triangle not found"}), 404


@app.route('/triangles', methods=['POST'])
def create_triangle():
    global triangles

    data = request.get_json()

    if not data:
        return jsonify({"error": "Missing JSON body"}), 400

    a = data.get("a")
    b = data.get("b")
    c = data.get("c")

    if a is None or b is None or c is None:
        return jsonify({"error": "Missing triangle sides"}), 400

    if a <= 0 or b <= 0 or c <= 0:
        return jsonify({"error": "Sides must be greater than zero"}), 400

    if a + b <= c or a + c <= b or b + c <= a:
        return jsonify({"error": "Not a valid triangle"}), 400

    triangle = Triangle(a, b, c)
    triangle_type = triangle.classify()

    new_id = max([t["id"] for t in triangles], default=0) + 1

    record = {
        "id": new_id,
        "a": a,
        "b": b,
        "c": c,
        "type": triangle_type
    }

    triangles.append(record)
    save_triangle(record)

    return jsonify(record), 201


@app.route('/triangles/<int:id>', methods=['DELETE'])
def delete_triangle(id):
    global triangles

    for triangle in triangles:
        if triangle["id"] == id:
            triangles.remove(triangle)
            return jsonify({"message": f"Triangle {id} deleted successfully"}), 200

    return jsonify({"error": "Triangle not found"}), 404


if __name__ == "__main__":
    app.run(debug=True)
