/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysAppointmentUpdateComponent } from 'app/entities/sys-appointment/sys-appointment-update.component';
import { SysAppointmentService } from 'app/entities/sys-appointment/sys-appointment.service';
import { SysAppointment } from 'app/shared/model/sys-appointment.model';

describe('Component Tests', () => {
    describe('SysAppointment Management Update Component', () => {
        let comp: SysAppointmentUpdateComponent;
        let fixture: ComponentFixture<SysAppointmentUpdateComponent>;
        let service: SysAppointmentService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysAppointmentUpdateComponent]
            })
                .overrideTemplate(SysAppointmentUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysAppointmentUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysAppointmentService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysAppointment(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysAppointment = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysAppointment();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysAppointment = entity;
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
