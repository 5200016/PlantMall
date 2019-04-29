/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysAppointmentDetailComponent } from 'app/entities/sys-appointment/sys-appointment-detail.component';
import { SysAppointment } from 'app/shared/model/sys-appointment.model';

describe('Component Tests', () => {
    describe('SysAppointment Management Detail Component', () => {
        let comp: SysAppointmentDetailComponent;
        let fixture: ComponentFixture<SysAppointmentDetailComponent>;
        const route = ({ data: of({ sysAppointment: new SysAppointment(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysAppointmentDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysAppointmentDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysAppointmentDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysAppointment).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
