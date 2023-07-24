import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICasconfirme, NewCasconfirme } from '../casconfirme.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICasconfirme for edit and NewCasconfirmeFormGroupInput for create.
 */
type CasconfirmeFormGroupInput = ICasconfirme | PartialWithRequiredKeyOf<NewCasconfirme>;

type CasconfirmeFormDefaults = Pick<NewCasconfirme, 'id'>;

type CasconfirmeFormGroupContent = {
  id: FormControl<ICasconfirme['id'] | NewCasconfirme['id']>;
  code_registre: FormControl<ICasconfirme['code_registre']>;
  lien_parente: FormControl<ICasconfirme['lien_parente']>;
  fiche: FormControl<ICasconfirme['fiche']>;
};

export type CasconfirmeFormGroup = FormGroup<CasconfirmeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CasconfirmeFormService {
  createCasconfirmeFormGroup(casconfirme: CasconfirmeFormGroupInput = { id: null }): CasconfirmeFormGroup {
    const casconfirmeRawValue = {
      ...this.getFormDefaults(),
      ...casconfirme,
    };
    return new FormGroup<CasconfirmeFormGroupContent>({
      id: new FormControl(
        { value: casconfirmeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      code_registre: new FormControl(casconfirmeRawValue.code_registre, {
        validators: [Validators.required],
      }),
      lien_parente: new FormControl(casconfirmeRawValue.lien_parente),
      fiche: new FormControl(casconfirmeRawValue.fiche, {
        validators: [Validators.required],
      }),
    });
  }

  getCasconfirme(form: CasconfirmeFormGroup): ICasconfirme | NewCasconfirme {
    return form.getRawValue() as ICasconfirme | NewCasconfirme;
  }

  resetForm(form: CasconfirmeFormGroup, casconfirme: CasconfirmeFormGroupInput): void {
    const casconfirmeRawValue = { ...this.getFormDefaults(), ...casconfirme };
    form.reset(
      {
        ...casconfirmeRawValue,
        id: { value: casconfirmeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CasconfirmeFormDefaults {
    return {
      id: null,
    };
  }
}
