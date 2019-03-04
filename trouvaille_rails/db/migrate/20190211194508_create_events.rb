class CreateEvents < ActiveRecord::Migration[5.2]
  def change
    create_table :events, id: :uuid do |t|
      t.string :title
      t.text :description
      t.datetime :start_time
      t.datetime :end_time
      t.uuid :venue_id, foreign_key: true
      t.timestamps
    end
  end
end
