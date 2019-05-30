/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysFormUpdateComponent } from 'app/entities/sys-form/sys-form-update.component';
import { SysFormService } from 'app/entities/sys-form/sys-form.service';
import { SysForm } from 'app/shared/model/sys-form.model';

describe('Component Tests', () => {
    describe('SysForm Management Update Component', () => {
        let comp: SysFormUpdateComponent;
        let fixture: ComponentFixture<SysFormUpdateComponent>;
        let service: SysFormService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysFormUpdateComponent]
            })
                .overrideTemplate(SysFormUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysFormUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysFormService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysForm(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysForm = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysForm();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysForm = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
