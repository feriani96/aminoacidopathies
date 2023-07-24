import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IStructuresante, NewStructuresante } from '../structuresante.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IStructuresante for edit and NewStructuresanteFormGroupInput for create.
 */
type StructuresanteFormGroupInput = IStructuresante | PartialWithRequiredKeyOf<NewStructuresante>;

type StructuresanteFormDefaults = Pick<NewStructuresante, 'id'>;

type StructuresanteFormGroupContent = {
  id: FormControl<IStructuresante['id'] | NewStructuresante['id']>;
  name: FormControl<IStructuresante['name']>;
};

export type StructuresanteFormGroup = FormGroup<StructuresanteFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class StructuresanteFormService {
  createStructuresanteFormGroup(structuresante: StructuresanteFormGroupInput = { id: null }): StructuresanteFormGroup {
    const structuresanteRawValue = {
      ...this.getFormDefaults(),
      ...structuresante,
    };
    return new FormGroup<StructuresanteFormGroupContent>({
      id: new FormControl(
        { value: structuresanteRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(structuresanteRawValue.name),
    });
  }

  getStructuresante(form: StructuresanteFormGroup): IStructuresante | NewStructuresante {
    return form.getRawValue() as IStructuresante | NewStructuresante;
  }

  resetForm(form: StructuresanteFormGroup, structuresante: StructuresanteFormGroupInput): void {
    const structuresanteRawValue = { ...this.getFormDefaults(), ...structuresante };
    form.reset(
      {
        ...structuresanteRawValue,
        id: { value: structuresanteRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): StructuresanteFormDefaults {
    return {
      id: null,
    };
  }
}
