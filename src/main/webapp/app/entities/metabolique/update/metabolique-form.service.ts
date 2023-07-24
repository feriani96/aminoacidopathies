import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMetabolique, NewMetabolique } from '../metabolique.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMetabolique for edit and NewMetaboliqueFormGroupInput for create.
 */
type MetaboliqueFormGroupInput = IMetabolique | PartialWithRequiredKeyOf<NewMetabolique>;

type MetaboliqueFormDefaults = Pick<NewMetabolique, 'id'>;

type MetaboliqueFormGroupContent = {
  id: FormControl<IMetabolique['id'] | NewMetabolique['id']>;
  name: FormControl<IMetabolique['name']>;
  fait: FormControl<IMetabolique['fait']>;
  laboratoire: FormControl<IMetabolique['laboratoire']>;
  resultat: FormControl<IMetabolique['resultat']>;
};

export type MetaboliqueFormGroup = FormGroup<MetaboliqueFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MetaboliqueFormService {
  createMetaboliqueFormGroup(metabolique: MetaboliqueFormGroupInput = { id: null }): MetaboliqueFormGroup {
    const metaboliqueRawValue = {
      ...this.getFormDefaults(),
      ...metabolique,
    };
    return new FormGroup<MetaboliqueFormGroupContent>({
      id: new FormControl(
        { value: metaboliqueRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(metaboliqueRawValue.name),
      fait: new FormControl(metaboliqueRawValue.fait),
      laboratoire: new FormControl(metaboliqueRawValue.laboratoire),
      resultat: new FormControl(metaboliqueRawValue.resultat),
    });
  }

  getMetabolique(form: MetaboliqueFormGroup): IMetabolique | NewMetabolique {
    return form.getRawValue() as IMetabolique | NewMetabolique;
  }

  resetForm(form: MetaboliqueFormGroup, metabolique: MetaboliqueFormGroupInput): void {
    const metaboliqueRawValue = { ...this.getFormDefaults(), ...metabolique };
    form.reset(
      {
        ...metaboliqueRawValue,
        id: { value: metaboliqueRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MetaboliqueFormDefaults {
    return {
      id: null,
    };
  }
}
