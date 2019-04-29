/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysAppointmentComponent } from 'app/entities/sys-appointment/sys-appointment.component';
import { SysAppointmentService } from 'app/entities/sys-appointment/sys-appointment.service';
import { SysAppointment } from 'app/shared/model/sys-appointment.model';

describe('Component Tests', () => {
    describe('SysAppointment Management Component', () => {
        let comp: SysAppointmentComponent;
        let fixture: ComponentFixture<SysAppointmentComponent>;
        let service: SysAppointmentService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysAppointmentComponent],
                providers: []
            })
                .overrideTemplate(SysAppointmentComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysAppointmentComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysAppointmentService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysAppointment(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysAppointments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
