ActiveAdmin.register Event do
    permit_params :title, :description, :start_time, :end_time, :venue

    index do
      selectable_column
      # id_column
      column :title
      column :description
      column :start_time
      column :end_time
      column :venue
      actions
    end

    filter :title
    filter :start_time
    filter :created_at

    form do |f|
      f.inputs do
        f.input :title
        f.input :description
        f.input :start_time
      end
      f.actions
    end
  end
